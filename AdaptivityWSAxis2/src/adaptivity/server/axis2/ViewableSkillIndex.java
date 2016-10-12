package adaptivity.server.axis2;

public class ViewableSkillIndex {
	private int playerIndex;
	private int skillIndex;

	public ViewableSkillIndex(int playerIndex, int skillIndex)
	{
		this.playerIndex = playerIndex;
		this.skillIndex = skillIndex;
	}
	public int getPlayerIndex()
	{
		return playerIndex;
	}
	public int getSkillIndex()
	{
		return skillIndex;
	}
	@Override
	public boolean equals(Object skill)
	{
		if(skill.getClass() == ViewableSkillIndex.class && this.playerIndex == ((ViewableSkillIndex) skill).getPlayerIndex() && this.skillIndex == ((ViewableSkillIndex) skill).getSkillIndex())
			return true;
		return false;
	}
}
