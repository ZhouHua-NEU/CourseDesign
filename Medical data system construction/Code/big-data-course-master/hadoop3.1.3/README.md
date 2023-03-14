# hadoop3的docker环境

## 1. 环境要求

- docker-compose2.x: Docker Compose是用来定义和运行复杂应用的docker工具. 通常一些大的应用需要由多个Docker容器组成, 如果使用命令行生成容器将会非常繁琐, docker-compose允许在一个文件中配置大量容器.

- docker-compose下载地址：https://gitcode.net/mirrors/docker/compose/-/releases/v2.12.2?spm=1033.2243.3001.5876

## 2. Docker镜像说明

​	由于原生的Docker Hub可能受限于国内网络, 下载速度非常低, 并且还存在镜像无法推送至仓库的风险. 因此, 笔者采用了[阿里云容器镜像服务](https://cr.console.aliyun.com)存储所需镜像. 另一方面, 整个Hadoop基础组件使用了三个镜像, 分别对应`cluster-master`、`cluster-slave1`、`cluster-slave2`, 而每个镜像开启一个容器(节点), 三个节点组成了一个**主/从架构网络**.

​	在master节点中, 开放了`9870`、`8088`、`9000`等端口, 而对于slave节点, 开放了`9864`和`8043`端口. 每个节点都将当前目录映射到节点内的`/data/hadoop`目录中

## 3. 使用方法

- 开启hadoop3: 在当前目录中运行命令`docker-compose up -d`
- 关闭hadoop3: 在当前目录中运行命令`docker-compose down`

## 4. 注意事项

- 本次需下载的镜像较大, 建议先通过wsl2修改docker镜像默认保存位置, 可参考文档https://zhuanlan.zhihu.com/p/494334649.
- 如果电脑资源(CPU、内存)不够开启2个从节点, 可以在docker-compose.yml文件中注释掉cluster-slave2节点, 以运行一个主节点、一个从节点组成的2节点集群.

- 开启前请先将包含`172.15.0.2/16`网段的子网删除, 例如`netgroup`子网可能包含该网段.
