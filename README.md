# Datura
>Datura(曼陀罗)是什么?她是一个正能量的希望工程,她的出现旨在帮助到那些阳光照射不到的地方的人们,消除人与人之间不平等,带他们带来一份温暖、一份阳光、一份希望。
项目采用组件化+MVP+RX等主流技术

####APK下载地址
https://fir.im/l23e?release_id=5b200bef959d697715c8262d
####开源地址
https://github.com/boatt/androidMoule


## 项目截图
![image.png](https://upload-images.jianshu.io/upload_images/2567841-1562ebc3d1806944.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
![image.png](https://upload-images.jianshu.io/upload_images/2567841-dcb75b21d3d860ac.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


![image.png](https://upload-images.jianshu.io/upload_images/2567841-44e78bd7428d00e4.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
![image.png](https://upload-images.jianshu.io/upload_images/2567841-de8e5493add6d983.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
![image.png](https://upload-images.jianshu.io/upload_images/2567841-3b42a35aa748c185.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
![image.png](https://upload-images.jianshu.io/upload_images/2567841-2517c14cc35954b9.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
![image.png](https://upload-images.jianshu.io/upload_images/2567841-2b8fb5b371563bc1.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
![image.png](https://upload-images.jianshu.io/upload_images/2567841-c1563008114743a1.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
![image.png](https://upload-images.jianshu.io/upload_images/2567841-31e052ab9d9e7aec.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
![image.png](https://upload-images.jianshu.io/upload_images/2567841-4c2ffa90921cbb89.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)



### 架构  
>组件化+MVP

#### 已经组件化的部分
> 登录模块
主页模块
新闻模块
商城模块

##### 如何切换模块化与集成化?

> 找到项目下的gradle.properties文件
isModule=false是切换开关,true打开模块化,false 是关闭模块化采用集成化

### 开源库

```
    api 'com.qmuiteam:qmui:1.0.6'
    api 'com.github.bumptech.glide:glide:3.7.0'
    api "io.reactivex.rxjava2:rxjava:2.0.1"
    api "io.reactivex.rxjava2:rxandroid:2.0.1"
    api "com.alibaba:arouter-api:1.3.1"
    api 'org.greenrobot:eventbus:3.0.0'
    api 'com.github.CymChad:BaseRecyclerViewAdapterHelper:2.9.28'
    api 'com.koushikdutta.async:androidasync:2.+'
    jbox2d

```

如果你觉得这个概念项目不错可以打赏我一杯咖啡钱



## License
```
Copyright (C) 2018 boatt

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License
```
