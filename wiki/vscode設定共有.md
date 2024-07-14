## Profileによるユーザー設定、拡張機能の共有
設定の共有にはSettings Syncという機能で共有するか、Profileを作成して配布するという2通りの方法がありそう
共有できる内容ほぼ同じよう

Settings SyncではMicrosoftアカウント or Githubアカウントが必要になるのですが、
プロジェクトで使用できるアカウントを両方とも持っていなかったのでProfileを配布することで設定を共有

https://qiita.com/The-town/items/dcb29a138908ac79a2ea  
https://zenn.dev/hankei6km/articles/vscode-profiles  
https://qiita.com/k_bobchin/items/717c216ddc29e5fbcd43

## ワークスペース設定
VSCodeでは特定のディレクトリを指定してワークスペースにできます。
画像のように、ファイル > 名前をつけてワークスペースに保存...　で保存できます。
![](https://storage.googleapis.com/zenn-user-upload/e412df98286d-20240511.png)

ワークスペースを保存すると、`[project名].code-workspace`の形式でファイルが作成されます。
そのファイルをクリックするとワークスペースをVSCodeで呼び出すことができます。
また、`[project名].code-workspace`の中身は以下のようになっています。
```json
{
	"folders": [
		{
			"path": "../../Desktop/sampleProject/appulication1"
		}
	],
	"settings": {
		"files.encoding": "utf8"
	}
}
```
* folders > path ...ワークスペースとして指定したディレクトリの相対パスです。
* settings ...ワークスペース単位のsettings.jsonです。
