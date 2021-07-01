package com.linran.serialize;

import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Logger;
import org.springframework.core.serializer.Serializer;

public class AbstractSerializer implements Serializable {
    public static final AbstractSerializer.NullSerializer NULL = new AbstractSerializer.NullSerializer();
    protected static final Logger log = Logger.getLogger(AbstractSerializer.class.getName());

    public AbstractSerializer() {
    }

    public void writeObject(Object obj, AbstractHessianOutput out) throws IOException {
      if (!out.addRef(obj)) {
        try {
          Object replace = this.writeReplace(obj);
          if (replace != null) {
            out.writeObject(replace);
            out.replaceRef(replace, obj);
            return;
          }
        } catch (RuntimeException var5) {
          throw var5;
        } catch (Exception var6) {
          throw new HessianException(var6);
        }

        Class<?> cl = this.getClass(obj);
        int ref = out.writeObjectBegin(cl.getName());
        if (ref < -1) {
          this.writeObject10(obj, out);
        } else {
          if (ref == -1) {
            this.writeDefinition20(cl, out);
            out.writeObjectBegin(cl.getName());
          }

          this.writeInstance(obj, out);
        }

      }
    }

    protected Object writeReplace(Object obj) {
      return null;
    }

    protected Class<?> getClass(Object obj) {
      return obj.getClass();
    }

    protected void writeObject10(Object obj, AbstractHessianOutput out) throws IOException {
      throw new UnsupportedOperationException(this.getClass().getName());
    }

    protected void writeDefinition20(Class<?> cl, AbstractHessianOutput out) throws IOException {
      throw new UnsupportedOperationException(this.getClass().getName());
    }

    protected void writeInstance(Object obj, AbstractHessianOutput out) throws IOException {
      throw new UnsupportedOperationException(this.getClass().getName());
    }

    static final class NullSerializer extends AbstractSerializer {
      NullSerializer() {
      }

      public void writeObject(Object obj, AbstractHessianOutput out) throws IOException {
        throw new IllegalStateException(this.getClass().getName());
      }
    }
  }


}
