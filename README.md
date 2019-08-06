# ColorBar
ColorBar

![](http://stars.costars.cn/%E5%BE%AE%E4%BF%A1%E5%9B%BE%E7%89%87_20190430103751.jpg)

> 1.依赖
```Java
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}

implementation 'com.github.lannaican:StarPlugin-ColorBar:1.0.2'
```
> 2.使用
```java
<com.star.plugin.colorbar.ColorBar
    android:id="@+id/colorBar"
    android:layout_width="match_parent"
    android:layout_height="26dp"/>

<com.star.plugin.colorbar.ColorValueBar
    android:id="@+id/colorValueBar"
    android:layout_width="match_parent"
    android:layout_height="26dp"
    android:layout_marginTop="10dp"/>

colorBar.setThumbColor(int);
colorBar.setThumbBorderColor(int);
colorBar.setThumbBorderWidth(float);
colorBar.setValueBar(ColorValueBar)
colorBar.setColor(int);
colorBar.addOnColorChangedListener(listener);
colorValueBar.addOnColorChangedListener(listener);
//最终颜色使用colorBar.getValueBar()或者colorValueBar.getColor()
```
