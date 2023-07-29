package com.example.busyshop.dto.requestdto;

import com.example.busyshop.Enum.Gender;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerRequestDto {

    String name;

    String email;

    String phone;

    Gender gender;
}
