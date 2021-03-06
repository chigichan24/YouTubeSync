# SyncPod-Android
SyncPodcのAndroidアプリ用のリポジトリです。

## 必要環境
* Android Studio 3.1
* Android SDK 26
* gradle 4.1


### Android Studio 3.1
[ここ](https://developer.android.com/studio/archive.html)からインストールする。

### Android SDK
Android Studio内のSDK Managerから、デバッグ用実機端末のバージョンに合ったSDKをダウンロードする。

## セットアップ
1. 次のコマンドを実行する。
```sh
git clone git@github.com:cyder/SyncPod-Android.git
cd SyncPod-Android
```
2. Android Studioでプロジェクトを開く

3. YouTube Data APIのAPIキーをセットする。
[Google Cloud Console](https://console.developers.google.com/)にアクセスして、YouTube Data API v3用のAPIキーを取得し、projectファイル直下にある`local.properties`に以下のように追記する．

```
developerKey=<取得したdeveloperKey>
```



4. デバッグ用実機端末を接続し、Runから起動させる。

## 著者
* [森 篤史](@Mori-Atsushi)
* [千北 一期](@chigichan24)
