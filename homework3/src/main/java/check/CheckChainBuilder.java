package check;

//билдер для цепочки событий
public class CheckChainBuilder {
    private CheckHandler head;
    private CheckHandler tail;

    public CheckChainBuilder addHandler(CheckHandler handler){
        if(head == null){
            head = handler;
            tail = handler;
        } else {
            tail.setNext(handler);
            tail = handler;
        }
        return this;
    }

    public CheckHandler build(){
        if(head == null){
            throw new IllegalStateException("Цепочка проверок пуста");
        }
        return  head;
    }
}
