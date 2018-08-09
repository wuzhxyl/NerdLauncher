
##

- FLAG_ACTIVITY_NEW_TASK
> 在新的任务中进行activity栈的处理.

- 默认的桌面启动器

```
<intent-filter>
    <action android:name="android.intent.action.MAIN"/>
    <category android:name="android.intent.category.HOME" />
    <category android:name="android.intent.category.DEFAULT" />
    <category android:name="android.intent.category.LAUNCHER"/>
</intent-filter>
```

- 启动应用

```
Intent intent = new Intent(Intent.ACTION_MAIN);
ActivityInfo activityInfo = mResolveInfo.activityInfo;
intent.setClassName(activityInfo.applicationInfo.packageName, activityInfo.name);
intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // 在一个新建的任务中打开Activity
startActivity(intent);
```

- 特殊情况

> Lollipop设备上会对android.intent.action.SEND/_MULTYPE的Intent
默认包含FLAG_ACTIVITY_NEW_TASK，此举为并发文档

- 需要启动多个任务

> . 添加Flag: FLAG+ACTIVITY_NEW_DOCUMENT

> . manifest文件中activity设置documentLaunchMode="infoExisting"

> 此两种皆为一份文档一个任务，若无论如何都要新任务。则
FLAG_ACTIVITY_NEW_DOCUMENT
FLAG_ACTIVITY_MULTYPE_TASK
或 infoExisting -> always
