package com.mastertheboss.jaxrs.backend.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@XmlRootElement(name = "response")
public class Response {

	private Team team;
	private List<Team> teamList;
	private Cave cave;
	private List<Cave> caveList;
	private int status;
	private Exception exception;

}
