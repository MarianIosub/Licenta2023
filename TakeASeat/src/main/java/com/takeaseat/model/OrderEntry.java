package com.takeaseat.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import static com.takeaseat.constants.StringConstants.ORDER_ENTRIES;
import static com.takeaseat.constants.StringConstants.ORDER_ENTRY_ID_COLUMN_NAME;


@Entity
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = ORDER_ENTRIES)
public class OrderEntry {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = ORDER_ENTRY_ID_COLUMN_NAME)
	private Long id;
	@NonNull
	private String name;
	@NonNull
	private Double price;
	@NonNull
	@Column(columnDefinition = "LONGBLOB")
	private String photoLink;
	@NonNull
	private Integer quantity;
}
