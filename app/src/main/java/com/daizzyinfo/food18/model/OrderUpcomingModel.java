package com.daizzyinfo.food18.model;

public class OrderUpcomingModel {

  String OrderId,OrderDate,OrderPrice,OrderAddress,OrderItmCount,OrderStatus;

  public OrderUpcomingModel(String orderId, String orderDate, String orderPrice, String orderAddress, String orderItmCount, String orderStatus) {
    OrderId = orderId;
    OrderDate = orderDate;
    OrderPrice = orderPrice;
    OrderAddress = orderAddress;
    OrderItmCount = orderItmCount;
    OrderStatus = orderStatus;
  }

  public String getOrderId() {
    return OrderId;
  }

  public void setOrderId(String orderId) {
    OrderId = orderId;
  }

  public String getOrderDate() {
    return OrderDate;
  }

  public void setOrderDate(String orderDate) {
    OrderDate = orderDate;
  }

  public String getOrderPrice() {
    return OrderPrice;
  }

  public void setOrderPrice(String orderPrice) {
    OrderPrice = orderPrice;
  }

  public String getOrderAddress() {
    return OrderAddress;
  }

  public void setOrderAddress(String orderAddress) {
    OrderAddress = orderAddress;
  }

  public String getOrderItmCount() {
    return OrderItmCount;
  }

  public void setOrderItmCount(String orderItmCount) {
    OrderItmCount = orderItmCount;
  }

  public String getOrderStatus() {
    return OrderStatus;
  }

  public void setOrderStatus(String orderStatus) {
    OrderStatus = orderStatus;
  }
}
