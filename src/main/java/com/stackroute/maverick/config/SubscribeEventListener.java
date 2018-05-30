/// **
// *
// */
// package com.stackroute.maverick.config;
//
// import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
// import org.springframework.stereotype.Component;
// import org.springframework.web.socket.messaging.SessionSubscribeEvent;
//
// @Component
// public abstract class SubscribeEventListener {
//
// public void onApplicationEvent(SessionSubscribeEvent sessionSubscribeEvent) {
// StompHeaderAccessor headerAccessor =
/// StompHeaderAccessor.wrap(sessionSubscribeEvent.getMessage());
// System.out.println(headerAccessor.getSessionAttributes().get("sessionId").toString());
// }
//
//// public void onApplicationEvent(SessionSubscribeEvent event) {
//// // TODO Auto-generated method stub
////
//// }
// }