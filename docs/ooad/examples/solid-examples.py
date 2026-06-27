class Rectangle:
    def __init__(self, width, height):
        self._width = width
        self._height = height

    def set_width(self, w):
        self._width = w

    def set_height(self, h):
        self._height = h

    def area(self):
        return self._width * self._height

class Square(Rectangle):
    def set_width(self, w):
        self._width = w
        self._height = w   # 破坏了父类行为

    def set_height(self, h):
        self._width = h
        self._height = h
"""
哪里违反 LSP？
你写一个函数：
传 Rectangle → 输出 50 ✅
传 Square → 输出 100 ❌（因为高和宽被强行同步）
"""
def resize_rectangle(r: Rectangle):
    r.set_width(5)
    r.set_height(10)
    print(r.area())   # 预期 50

"""
# 正确做法
class Shape:
    def area(self): pass

class Rectangle(Shape):
    # 独立实现宽、高

class Square(Shape):
    # 独立实现边长
"""


# 接下来是DIP，这里高层策略（powerswitch）直接依赖于LightBulb，如果之后想换成风扇等，又得重新修改powerswitch代码
"""
class LightBulb:
    def turn_on(self):
        print("灯泡亮")

    def turn_off(self):
        print("灯泡灭")

class PowerSwitch:
    def __init__(self, bulb: LightBulb):
        self.bulb = bulb
        self.on = False

    def press(self):
        if self.on:
            self.bulb.turn_off()
        else:
            self.bulb.turn_on()
        self.on = not self.on
"""

# ✅ 正确做法：依赖抽象

from abc import ABC, abstractmethod

class Switchable(ABC):
    @abstractmethod
    def turn_on(self): pass
    @abstractmethod
    def turn_off(self): pass

class LightBulb(Switchable):
    def turn_on(self): print("灯泡亮")
    def turn_off(self): print("灯泡灭")

class Fan(Switchable):
    def turn_on(self): print("风扇转")
    def turn_off(self): print("风扇停")
# 使用方法
class PowerSwitch:
    def __init__(self, device: Switchable):
        self.device = device
        self.on = False

    def press(self):
        if self.on:
            self.device.turn_off()
        else:
            self.device.turn_on()
        self.on = not self.on