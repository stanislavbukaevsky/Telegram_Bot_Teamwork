package pro.sky.telegrambotteamwork.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import java.util.Date;


@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
public class ReportData {
    @Id
    @GeneratedValue
    private long id;

    private Long chatId;

    private String ration;

    private String health;

    private String habits;

    private long days;

    private String filePath;

    private long fileSize;

    @Lob
    private byte[] data;

    private String caption;

    private Date lastMessage;

    private Long lastMessageMs;


}