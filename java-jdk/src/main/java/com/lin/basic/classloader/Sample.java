package com.lin.basic.classloader;

/**
 * @author: linran
 * @email: linran@live.com
 * @create: 2019-09-22 12:57
 * @Description:
 **/
public class Sample {
    private Sample instance;

    public void setSample(Object instance) {
        this.instance = (Sample) instance;
    }
}
