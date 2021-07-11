package com.linran.utils;

import com.google.common.base.Preconditions;
import com.linran.protocol.RpcProtocol;
import com.linran.protocol.ServiceInfo;
import java.util.Collections;
import java.util.List;

public final class ServiceUtils {

  public static final String SERVICE_SPLIT_TOKEN = "#";

  public static List<ServiceInfo> getServiceInfos(List<String> serviceKeys) {
    if (CollectionUtils.isEmpty(serviceKeys)) {
      return Collections.emptyList();
    }
    return ConvertTools.toList(serviceKeys, ServiceInfo::valueOfKey);
  }

  public static List<String> makeKeys(List<ServiceInfo> serviceInfos) {
    return ConvertTools.toList(serviceInfos, ServiceInfo::makeKey);
  }

  public static String getKey(String interfaceName, String version) {
    Preconditions.checkArgument(StringUtils.isNotEmpty(interfaceName));
    return interfaceName + SERVICE_SPLIT_TOKEN + version;
  }

  public static boolean isAvailableServer(RpcProtocol server) {
    return server != null && CollectionUtils.isNotEmpty(server.getServiceInfos());
  }

}
