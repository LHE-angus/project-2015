package com.ebiz.mmt.domain;

import java.io.Serializable;
import java.util.Date;

import com.ebiz.ssi.domain.BaseDomain;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-06-08 10:31:47
 */
public class EcSpecFbUgdetail extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;

	private Long match_id;

	private Long guess_user_id;

	private Date guess_date;

	private Integer guess_win;

	private Integer guess_a_team_goal;

	private Integer guess_b_team_goal;

	private Integer guess_win_gift;

	private Integer guess_goal_gift;

	public EcSpecFbUgdetail() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getMatch_id() {
		return match_id;
	}

	public void setMatch_id(Long match_id) {
		this.match_id = match_id;
	}

	/**
	 * @val 竞猜用户ID
	 */
	public Long getGuess_user_id() {
		return guess_user_id;
	}

	/**
	 * @val 竞猜用户ID
	 */
	public void setGuess_user_id(Long guess_user_id) {
		this.guess_user_id = guess_user_id;
	}

	/**
	 * @val 竞猜时间
	 */
	public Date getGuess_date() {
		return guess_date;
	}

	/**
	 * @val 竞猜时间
	 */
	public void setGuess_date(Date guess_date) {
		this.guess_date = guess_date;
	}

	/**
	 * @val 竞猜输赢,1表示主队赢，0表示平，-1表示输
	 */
	public Integer getGuess_win() {
		return guess_win;
	}

	/**
	 * @val 竞猜输赢,1表示主队赢，0表示平，-1表示输
	 */
	public void setGuess_win(Integer guessWin) {
		guess_win = guessWin;
	}

	/**
	 * @val 竞猜主队得分
	 */
	public Integer getGuess_a_team_goal() {
		return guess_a_team_goal;
	}

	/**
	 * @val 竞猜主队得分
	 */
	public void setGuess_a_team_goal(Integer guess_a_team_goal) {
		this.guess_a_team_goal = guess_a_team_goal;
	}

	/**
	 * @val 竞猜客队得分
	 */
	public Integer getGuess_b_team_goal() {
		return guess_b_team_goal;
	}

	/**
	 * @val 竞猜客队得分
	 */
	public void setGuess_b_team_goal(Integer guess_b_team_goal) {
		this.guess_b_team_goal = guess_b_team_goal;
	}

	/**
	 * @val 竞猜输赢得积分
	 */
	public Integer getGuess_win_gift() {
		return guess_win_gift;
	}

	/**
	 * @val 竞猜输赢得积分
	 */
	public void setGuess_win_gift(Integer guess_win_gift) {
		this.guess_win_gift = guess_win_gift;
	}

	/**
	 * @val 竞猜得分赢得积分数
	 */
	public Integer getGuess_goal_gift() {
		return guess_goal_gift;
	}

	/**
	 * @val 竞猜得分赢得积分数
	 */
	public void setGuess_goal_gift(Integer guess_goal_gift) {
		this.guess_goal_gift = guess_goal_gift;
	}

}