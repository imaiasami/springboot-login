package com.example.login.model;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Board {

	private long board_id;
	private String title;
	private String content;
	private int hit;
	private LocalDateTime input_date;
	private String id;

}
