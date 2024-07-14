
# レイヤーアーキテクチャ
https://qiita.com/lobin-z0x50/items/39131a4f47ed7c5df443

# Spring Batch
https://terasoluna-batch.github.io/guideline/5.0.0.RELEASE/ja/Ch02_SpringBatchArchitecture.html

# ORM
https://www.docswell.com/s/MasatoshiTada/596WW5-how-to-choose-java-orm#p5
https://qiita.com/keita_ide78/items/48fd62e9505d2ddad51f

# lombok使い方
各種アノテーションを付与するだけでJavaのお決まりのコード（ボイラープレートコード）を生成できる  
  
https://qiita.com/supreme0110/items/391505da8f4321736421
  
delombok ...lombokで生成されるコードを確認できる  
https://blog.y-yuki.net/entry/2016/11/15/000000

boolean型のgetterを生成 ・・・getXXXという命名ではなく、isXXXという命名になる。

# Spring Data JDBC
Spring JDBCを更に拡張。  
https://speakerdeck.com/rshindo/jsug-2019-01  
https://spring.io/blog/2021/09/09/spring-data-jdbc-how-to-use-custom-id-generation  
https://qiita.com/kuchita_el/items/33e43b6cfc6f2f2bb393  

Entityの作成
* @Entityは不要
* 主キーには@Idを付与
* setter不要でimmutableにもできる。withメソッドが必要になる。  

Repositoryの作成
* CrudRepositoryをextendsしたRepositoryインターフェースを作成するのみ  
* CrudRepositoryに定義された各CRUDメソッドが利用可能  

    |メソッド|戻り値|
    |--|--|
    |sava(T entity)|T|
    |findById(ID id)|Optional<T>|
    |existsById(ID id)|boolean|
    |findAll()|Iterable<T>|
    |findAllById(Iterable<ID> ids|Iterable<T>|
    |count()|long|
    |deleteById(ID id)|void|
    |delete(T entity)|void|
* @Queryで任意のクエリを実行できる
  @Query("SELECT * FROM employee WHERE first_name = :firstName")  
  `List<Employee> findByFirstName(@Param("firstName") String firstName);`
* カスタムリポジトリも作成できる。

できないこと
* ページング＆ソート（クエリとして書くことは可能）
* メソッド名からクエリの自動生成
* 複合主キー（回避方法あり）
* 主キーに任意の値を設定（回避方法あり）　→　サロゲートキーじゃない主キー採用時に注意

リレーション
Spring Data JDBCは関連オブジェクトの永続化操作をサポート
* One-To-One,One-To-Manyの関連をサポート

そのた
* Events Repositoryの各操作に対していイベントトリガーを仕込むことが可能。
* Auditing 監査用カラム（作成者、作成日時など）への値設定。`@CreatedBy``@CreatedDate`

#### 主キーに任意の値を設定 
Entityクラスをpersistableインターフェースを実装して作成。  
persitableはsaveメソッド実行時のselect文を省略する  
saveメソッド実行時にはまず、更新対象をselectすることで、InsertするかUpdateするかを分岐している。
persistableのisNewメソッドを実装することで、ロジックを省略できる。

# @Autowired
https://qiita.com/yuto-hatano/items/69d01343f710117e4243
* フィールドインジェクション（非推奨）
* セッターインジェクション
* コンストラクタインジェクション（推奨）

# DI
https://qiita.com/suema0331/items/b7f3a19022198081f8d8  
https://qiita.com/kazuki43zoo/items/7a0e96573e930ac934ed  
https://zenn.dev/mikann_mikann/scraps/0f2261b389f7a4  
https://zenn.dev/micy/books/f95e071baa09b5/viewer/11f5dc  
https://terasolunaorg.github.io/guideline/current/ja/index.html

# JSONを扱う
* Jackson
* Gson
* JSON-P
* 
