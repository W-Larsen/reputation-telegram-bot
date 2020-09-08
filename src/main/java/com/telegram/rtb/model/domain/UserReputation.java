package com.telegram.rtb.model.domain;

import com.telegram.rtb.model.domain.key.UserReputationPk;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.ZonedDateTime;

/**
 * User reputation model.
 */
@Entity
@Table(name = "user_reputations")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserReputation {

    @EmbeddedId
    private UserReputationPk userReputationPk;
    private Integer reputationValue;
    private ZonedDateTime updatedDateTime;
    private Integer updatedFromId;

    @ManyToOne
    @JoinColumn(name = "chat_id", insertable = false, updatable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private TelegramChat telegramChat;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private TelegramUser telegramUser;

}
