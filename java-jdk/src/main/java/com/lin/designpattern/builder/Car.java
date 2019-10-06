package com.lin.designpattern.builder;

/**
 * @author: linran
 * @email: linran@live.com
 * @create: 2019-10-06 15:39
 * @Description: from https://juejin.im/post/5c2088205188251920598d85
 * 提供无参的构造函数，暴露一些公共的方法让用户自己去设置对象属性，这种方法较之第一种似乎增强了灵活度，用户可以根据自己的需要随意去设置属性。但是这种方法自身存在严重的缺点：
 * 因为构造过程被分到了几个调用中，在构造中 JavaBean 可能处于不一致的状态。类无法仅仅通过判断构造器参数的有效性来保证一致性。还有一个严重的弊端是，JavaBeans 模式阻止了把类做成不可变的可能。，这就需要我们付出额外的操作来保证它的线程安全。
 **/
public final class Car {
    /**
     * 必需属性
     */
    final String carBody;//车身
    final String tyre;//轮胎
    final String engine;//发动机
    final String aimingCircle;//方向盘
    final String safetyBelt;//安全带
    /**
     * 可选属性
     */
    final String decoration;//车内装饰品

    /**
     * car 的构造器 持有 Builder,将builder制造的组件赋值给 car 完成构建
     *
     * @param builder
     */
    public Car(Builder builder) {
        this.carBody = builder.carBody;
        this.tyre = builder.tyre;
        this.engine = builder.engine;
        this.aimingCircle = builder.aimingCircle;
        this.decoration = builder.decoration;
        this.safetyBelt = builder.safetyBelt;
    }

    public String getCarBody() {
        return carBody;
    }

    public String getTyre() {
        return tyre;
    }

    public String getEngine() {
        return engine;
    }

    public String getAimingCircle() {
        return aimingCircle;
    }

    public String getSafetyBelt() {
        return safetyBelt;
    }

    public String getDecoration() {
        return decoration;
    }

    public static final class Builder {
        String carBody;
        String tyre;
        String engine;
        String aimingCircle;
        String decoration;
        String safetyBelt;

        public Builder() {
            this.carBody = "宝马";
            this.tyre = "宝马";
            this.engine = "宝马";
            this.aimingCircle = "宝马";
            this.decoration = "宝马";
        }

        /**
         * 实际属性配置方法
         *
         * @param carBody
         * @return
         */
        public Builder carBody(String carBody) {
            this.carBody = carBody;
            return this;
        }

        public Builder tyre(String tyre) {
            this.tyre = tyre;
            return this;
        }

        public Builder safetyBelt(String safetyBelt) {
            if (safetyBelt == null) throw new NullPointerException("没系安全带，不要开车！");
            this.safetyBelt = safetyBelt;
            return this;
        }

        public Builder engine(String engine) {
            this.engine = engine;
            return this;
        }

        public Builder aimingCircle(String aimingCircle) {
            this.aimingCircle = aimingCircle;
            return this;
        }

        public Builder decoration(String decoration) {
            this.decoration = decoration;
            return this;
        }

        /**
         * 最后创造出实体car
         *
         * @return
         */
        public Car build() {
            return new Car(this);
        }
    }
}
