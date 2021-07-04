package com.linran.protocol;

import static com.linran.utils.ServiceUtils.SERVICE_SPLIT_TOKEN;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.linran.utils.ServiceUtils;
import com.linran.utils.StringUtils;
import java.io.Serializable;
import javax.annotation.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServiceInfo implements Serializable {

  private static final Logger log = LoggerFactory.getLogger(ServiceInfo.class);

  private static final long serialVersionUID = 6078324345390601573L;

  private String name;
  private String version;

  public static ServiceInfo valueOfKey(
      final String key
  ) {
    Preconditions.checkArgument(StringUtils.isNotEmpty(key));
    String[] arr = key.split(SERVICE_SPLIT_TOKEN, 2);
    ServiceInfo target = new ServiceInfo();
    target.setName(arr[0]);
    target.setVersion(arr[1]);
    return target;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  @Nullable
  public String makeKey() {
    return ServiceUtils.getKey(name, version);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof ServiceInfo)) {
      return false;
    }
    ServiceInfo that = (ServiceInfo) o;
    return Objects.equal(getName(), that.getName()) && Objects.equal(getVersion(), that.getVersion());
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(getName(), getVersion());
  }
}
