package com.sabetski.edi.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(schema = "main", name = "document")
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "main.document_id_serial")
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "number", nullable = false, unique = true, length = 40)
    private String number;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id")
    private User user;

    public Document(String number, User user) {
        this.number = number;
        this.user = user;
    }

    @Override
    public String toString() {
        return "Document{" + "documentId=" + id + ", number=" + number + ", user=" + user + "}\n";
    }
}