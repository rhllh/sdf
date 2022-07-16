package vttp2022.day6b.workshop;

public class BoxG<T> {
    private T content;      // generic (auto boxing)

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

}
