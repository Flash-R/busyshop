package com.example.busyshop.dto.requestdto;

import com.example.busyshop.Enum.CardType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CardRequestDto {
    String cardNo;

    int cvv;

    Date validTill;

    CardType cardType;

    String customerEmail;
}
