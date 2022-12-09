package ru.job4j.pooh;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class TopicService implements Service {
    private final ConcurrentHashMap<String, ConcurrentHashMap<String, ConcurrentLinkedQueue<String>>> topics
            = new ConcurrentHashMap<>();
   @Override
    public Resp process(Req req) {
       String text = "";
       String status = "200";
       if ("POST".equals(req.httpRequestType())) {
           for (ConcurrentLinkedQueue<String> queue : topics.getOrDefault(
                   req.getSourceName(), new ConcurrentHashMap<>()).values()) {
               queue.add(req.getParam());
           }
       } else if ("GET".equals(req.httpRequestType())) {
           topics.putIfAbsent(req.getSourceName(), new ConcurrentHashMap<>());
           topics.get(req.getSourceName()).putIfAbsent(req.getParam(), new ConcurrentLinkedQueue<>());
           text = topics.get(req.getSourceName()).get(req.getParam()).poll();
       }
       if (text == null) {
           text = "";
           status = "204";
       }
       return new Resp(text, status);
    }
}
