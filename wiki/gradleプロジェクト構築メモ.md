# gradle構築時メモ

### gradle/wrapper/gradle-wrapper.properties
spring initializrを使用するとgradlewラッパーが最新バージョンに指定された状態となるよう。  
gradle-wrapper.propertiesの`distributionUrl`のバージョンを修正することで指定できる。

### dependencies
|メソッド名|説明||
|--|--|--|
|compile||
|compileOnly|コンパイル時にのみ必要な依存関係。アプリ実行時に不要。lombokなどのアノテーションプロセッサーなどはこれ|
|implementation|プロジェクトのコンパイル時と実行時に必要|
|api|マルチモジュールプロジェクトで使用。このプロジェクトに依存している依存先にも影響する。|
|testImplementation|テストコンパイルとテスト実行時にのみ必要|
|runtimeOnly|アプリ実行時にのみ必要。特定のJDBCドライバーなど。|
|developmentOnly|開発時にのみ必要な依存関係。|
|||

* アノテーションプロセッサー
アノテーションプロセッサは、コンパイル時に、アノテーションに基づいてコードを検証、生成する仕組みです(プリプロセッサ)。 アノテーションを付与したクラスなどを用意すれば、コンパイル中に対象に応じた操作を挟みこむことができます。