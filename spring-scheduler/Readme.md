
## 注意

**注意 spring 与 quartz 的版本**

quartz 1.8.* 里`org.quartz.*Trigger` 是类。
而 2.* 里是接口。

版本不同 applicationContext.xml 里的配置是不同的。

另： `org.springframework.scheduling.timer.*` 包已不建议使用。
