package com.telegram.rtb.model.domain.key;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Composite key for keywords.
 */
@Embeddable
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KeywordPk implements Serializable {

    @Column(name = "keyword_id")
    private String keywordId;
    @Column(name = "chat_id")
    private Long chatId;

}
