package ru.job4j.pooh;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class QueueService implements Service {
    private final Map<String, ConcurrentLinkedQueue<String>> queue = new ConcurrentHashMap<>();

    @Override
    public Resp process(Req req) {
        String text = "";
        String status = HttpResponseStatus.OK;
        if (POST.equals(req.httpRequestType())) {
            queue.putIfAbsent(req.getSourceName(), new ConcurrentLinkedQueue<>());
            queue.get(req.getSourceName()).add(req.getParam());
        } else if (GET.equals(req.httpRequestType())) {
            text = queue.getOrDefault(
                    req.getSourceName(), new ConcurrentLinkedQueue<>()).poll();
        }
        if (text == null) {
            text = "";
            status = HttpResponseStatus.OK;
        }
        return new Resp(text, status);
    }
}
