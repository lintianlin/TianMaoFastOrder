# TianMaoFastOrder
天猫秒单神器

## 设想
  双11的时候用于再某个时间点自动完成下单支付流程，根据网络请求时间计算开始时间点。
  主要是利用了无障碍辅助服务(AccessibilityService).
## 遇到的问题
  在天猫最后的密码输入页面，无障碍辅助服务无法触发密码输入键盘的点击事件，还没有找到原因所在。