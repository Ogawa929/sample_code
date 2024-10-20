# EC2インスタンス構築メモ

## 料金
|サービス名|課金内容|備考|
|--|--|--|
|EC2(t2.micro)|起動時間 $0.0116/h <br>データ転送(イン) 0.00GB <br>データ転送(アウト) 0.114GB(最初の10TB/月)|750時間の無料枠有り 0.0116 * 24 * 30 = $8.352（約1250円）<br>イン方向は無料<br>アウト方向は有料|
|EBS|gp3ストレージ $0.096/GB 月 <br>gp2ボリューム 1ヶ月にプロビジョニングされたストレージ辺り $0.12/GB |<br>gp2ボリュームは30GBまで無料|
|Elastic IP|割当IPが稼働していない時間　$0.005/h<br>インスタンス停止中| 0.005 * 24 * 30 = $3.6（約540円） |
|NAT Gateway|１台辺り起動時間 $0.062/h <br>処理データ量 $0.062/GB| 0.062 * 24 * 30 = $44.64（約6700円）<br> 高いのでひとまず使わない（private subnetを使わない）|
||||


## EC2構築時の設定
### sudo設定、ユーザーの追加
1. sshキーで接続できるようにしておく。  
   1. セキュリティグループでport 22を開放しておく。接続元のIPが固定の場合は指定しておくと安全。
   1. キーペアを作成してEC2に割り当てる。
1. sudoコマンドにrootユーザーの昇格時にパスワードを設定しておく。
   1. rootユーザーに切り替え
   1. `passwd`コマンドでrootユーザーのパスワード設定
   1. `vi /etc/sudoers.d/90-cloud-init-users`を以下のように設定。
      ```
      ec2-user ALL=(ALL) ALL
      Defaults:ec2-user rootpw
      ```
1. ユーザーを追加する。
   1. 最初にグループを作っておく　`groupadd --gid *** [group_name]`
   1. ユーザーを作成　`useradd -g *** -u *** [user_name]`
   1. sudoできるように設定
      * `usermod -aG wheel [user_name]`
      * `/etc/sudoers.d/`配下のec2-userの設定をコピーして、追加ユーザー名で設定を追加する。
1. sshログインできるように設定する
   1. ec2-userの公開鍵を追加ユーザーのhomeディレクトリにコピー。
      * `cp -r /home/ec2-user/.ssh /home/[user_name]`
      * `chmod 700 /home/[user_name]/.ssh`
      * `chown -R [user_name]:[user_group] /home/[user_name]/.ssh`

### Dockerインストール
1. パッケージとパッケージキャッシュを更新 `sudo yum update -y`
1. 最新のDocker Community Editionをインストール`sudo yum -y install docker`
1. docker起動 `sudo systemctl start docker`
1. docker自動起動 `sudo systemctl enable docker`
1. dockerの動作確認 `sudo docker run hello-world`
1. 一般ユーザーにdocker操作権限の付与 `sudo usermod -aG docker [user_name]`<br>※グループの反映にはセッションの更新が必要
