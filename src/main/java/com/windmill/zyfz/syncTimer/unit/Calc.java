package com.windmill.zyfz.syncTimer.unit;


import java.math.BigDecimal;
import java.math.MathContext;

/**
 * @作者: Supalle
 * @时间: 2019/3/8
 * @描述: 简单公式计算器
 */
public class Calc {


    private char[] val;

    private int len;

    private int inx;


    // 构造器，把公式传进去，比如： 100 + 20 * 5 + (1 + 2)
    public Calc(String val) {
        this.val = val.toCharArray();
        len = this.val.length;
        inx = 0;
    }

    // 获取计算结果，使用方法其实就是 new Calc("100 + 20 * 5 + (1 + 2)").getResult()；就可以得到结果了
    public BigDecimal getResult() {
        return nextValue(BigDecimal.ZERO, '+');
    }

    // OK，接下来的两个方法，必须要弄明白，下一个值和下一个参数的区别
// 为什么要获取下一个值，加法、减法、和左小阔号，都需要获取下一个值，因为加法、减法如果碰到乘法、除法，那么运算优先权在右侧，如果碰到左侧小括号，优先权也在右侧，所以要先把右边的值算出来
// 为什么要获取下一个参数，乘法、除法，他们下一个运算符如果不是左侧小括号，那么应该从左往右顺序计算，因此需要直接取到下一个参数进行计算
// 还有一点要值得注意，那就是：在运算时，减法一律替换成加上一个负数，以此来消除实际对一个负数进行运算产生异常，比如 1 * -3，总不能检测到 - 的时候，又去做减法运算吧
// 就讲这么多了，不能理解的同学，再反复推敲几遍
    // 获取下一个值，传入第一个参数和第一个参数后的运算符
    private BigDecimal nextValue(BigDecimal param1, char operator) {
        if (inx < len) {
            if (operator == ')') {
                return param1;
            }

            if (operator == '+') {
                return param1.add(nextValue(nextParam(), inx < len ? val[inx++] : ')'));
            } else if (operator == '*') {
                return nextValue(param1.multiply(nextParam(), MathContext.DECIMAL128), inx < len ? val[inx++] : ')');
            } else if (operator == '/') {
                return nextValue(param1.divide(nextParam(), MathContext.DECIMAL128), inx < len ? val[inx++] : ')');
            }
        }
        return param1;
    }

    // 获取下一个参数
    private BigDecimal nextParam() {

        char[] param = new char[len - inx + 1];

        int paramInx = 0;

        while (inx < len) {

            if (val[inx] == '-') {
                if (paramInx == 0) {
                    param[paramInx++] = val[inx];
                    param[paramInx++] = '0';
                } else {
                    val[--inx] = '+';
                    break;
                }
            } else if (val[inx] == '.' || ((int) val[inx] >= 48 && (int) val[inx] <= 57)) {// 如果是 . 或 0 ~ 9
                param[paramInx++] = val[inx];
            } else if (val[inx] == '(') {
                inx++;
                return nextValue(BigDecimal.ZERO, '+');
            } else if (((int) val[inx] >= 41 && (int) val[inx] <= 43) || (int) val[inx] == 47) {
                break;
            }

            inx++;
        }
        return paramInx > 0 ? new BigDecimal(param, 0, paramInx) : BigDecimal.ZERO;
    }
}
