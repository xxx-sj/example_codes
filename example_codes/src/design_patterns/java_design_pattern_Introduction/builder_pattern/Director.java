package design_patterns.java_design_pattern_Introduction.builder_pattern;

public class Director {
    private Builder builder;

    public Director(Builder builder) {
        this.builder = builder;
    }
    
    public void construct() {
        builder.makeTitle("Greeting");
        builder.makeString("아침과 낮에");
        builder.makeItems(new String[] {"좋은 아침입니다.", "안녕하세요"});
        builder.makeString("밤에");
        builder.makeItems(new String[] {
                "ㅇ난녕하세요,",
                "안녕히 주무세요.ㅣ",
                "안녕히 계세요"
        });

        builder.close();
    }
}
