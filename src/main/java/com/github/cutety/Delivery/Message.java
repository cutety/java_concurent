package com.github.cutety.Delivery;

final class Message {
    private int id;
    private Object mail;

    public int getId() {
        return id;
    }

    public Object getMail() {
        return mail;
    }

    public Message(int id, Object mail) {
        this.id = id;
        this.mail = mail;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", mail=" + mail +
                '}';
    }
}
