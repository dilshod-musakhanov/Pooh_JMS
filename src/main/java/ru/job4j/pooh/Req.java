package ru.job4j.pooh;

public class Req {
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private final String httpRequestType;
    private final String poohMode;
    private final String sourceName;
    private final String param;

    public Req(String httpRequestType, String poohMode, String sourceName, String param) {
        this.httpRequestType = httpRequestType;
        this.poohMode = poohMode;
        this.sourceName = sourceName;
        this.param = param;
    }

    public static Req of(String content) {
        String[] strings = content.split(LINE_SEPARATOR);
        String httpRequestType = strings[0].split(" ")[0];
        String[] mode = strings[0].split("/");
        String poohMode = mode[1];
        String sourceName = mode[2].split(" ")[0];
        String param;
        if (strings[strings.length - 1].contains("=")) {
            param = strings[strings.length - 1].trim();
        } else {
            param = "";
        }
        if ("GET".equals(httpRequestType) && "topic".equals(poohMode) && "".equals(param)) {
            param = mode[3].split(" ")[0];
        }
        return new Req(httpRequestType, poohMode, sourceName, param);
    }

    public String httpRequestType() {
        return httpRequestType;
    }

    public String getPoohMode() {
        return poohMode;
    }

    public String getSourceName() {
        return sourceName;
    }

    public String getParam() {
        return param;
    }
}
