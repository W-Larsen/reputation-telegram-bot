package com.telegram.rtb.model.domain.key;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Composite key for user reputation.
 */
@Embeddable
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserReputationPk implements Serializable {

    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "chat_id")
    private Long chatId;

}
