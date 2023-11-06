package design_patterns.java_design_pattern_Introduction.builder_pattern;

public class Main {

    private static String test = "html";
    public static void main(String[] args) {

        if("plain".equals(test)) {
            TextBuilder textBuilder = new TextBuilder();
            Director director = new Director(textBuilder);
            director.construct();
            String result = textBuilder.getResult();
            System.out.println(result);
        } else if ("html".equals(test)) {
            HTMLBuilder htmlBuilder = new HTMLBuilder();
            Director director = new Director(htmlBuilder);
            director.construct();
            String result = htmlBuilder.getResult();
            System.out.println(result + "가 작성되었습니다.");
        }
    }
}
