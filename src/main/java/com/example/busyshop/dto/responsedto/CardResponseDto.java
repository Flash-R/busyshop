package com.example.busyshop.dto.responsedto;

import com.example.busyshop.Enum.CardType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class CardResponseDto {
    String cardNo;

    Date validTill;

    CardType cardType;

    String customerEmail;
}
