package com.base.game.character.body;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.base.game.character.GameCharacter;
import com.base.game.character.body.types.OrificeInterface;
import com.base.game.character.body.valueEnums.Capacity;
import com.base.game.character.body.valueEnums.OrificeElasticity;
import com.base.game.character.body.valueEnums.OrificeModifier;
import com.base.game.character.body.valueEnums.OrificePlasticity;
import com.base.game.character.body.valueEnums.Wetness;
import com.base.game.dialogue.utils.UtilText;

public class OrificeUrethra implements OrificeInterface, Serializable {
	private static final long serialVersionUID = 1L;
	
	private int wetness, elasticity, plasticity;
	private float capacity, stretchedCapacity;
	private boolean virgin;
	private Set<OrificeModifier> orificeModifiers;

	public OrificeUrethra(int wetness, int capacity, int elasticity, int plasticity, boolean virgin, Collection<OrificeModifier> orificeModifiers) {
		this.wetness = wetness;
		this.capacity = capacity;
		stretchedCapacity = capacity;
		this.elasticity = elasticity;
		this.plasticity = plasticity;
		this.virgin = virgin;
		
		this.orificeModifiers = new HashSet<>();
		for(OrificeModifier om : orificeModifiers) {
			this.orificeModifiers.add(om);
		}
	}
	
	@Override
	public Wetness getWetness(GameCharacter owner) {
		return Wetness.valueOf(wetness);
	}

	@Override
	public String setWetness(GameCharacter owner, int wetness) {
		if(!owner.hasPenis()) {
			return "<p style='text-align:center;'>[style.colourDisabled(Nothing happens...)]</p>";
		}
		
		int wetnessChange = 0;
		
		if (wetness <= 0) {
			if (this.wetness != 0) {
				wetnessChange = 0 - this.wetness;
				this.wetness = 0;
			}
		} else if (wetness >= Wetness.SEVEN_DROOLING.getValue()) {
			if (this.wetness != Wetness.SEVEN_DROOLING.getValue()) {
				wetnessChange = Wetness.SEVEN_DROOLING.getValue() - this.wetness;
				this.wetness = Wetness.SEVEN_DROOLING.getValue();
			}
		} else {
			if (this.wetness != wetness) {
				wetnessChange = wetness - this.wetness;
				this.wetness = wetness;
			}
		}
		
		if(wetnessChange == 0) {
			if(owner.isPlayer()) {
				return "<p style='text-align:center;'>[style.colourDisabled(Your precum production doesn't change...)]</p>";
			} else {
				return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name]'s precum production doesn't change...)]</p>");
			}
		}
		
		String wetnessDescriptor = getWetness(owner).getDescriptor();
		if (wetnessChange > 0) {
			if (owner.isPlayer()) {
				return "<p>"
							+ "Your [pc.eyes] widen as you feel your [pc.cock+] suddenly grow hard, and you let out [pc.a_moan+] as you feel a slick stream of precum oozing out of the tip as its production [style.boldGrow(increases)].</br>"
							+ "You now have [style.boldSex(" + UtilText.generateSingluarDeterminer(wetnessDescriptor) + " " + wetnessDescriptor + " urethra)]!"
						+ "</p>";
			} else {
				return UtilText.parse(owner, 
						"<p>"
							+ "[npc.Name]'s [npc.eyes] widen as [npc.she] feels [npc.her] [npc.cock+] suddenly grow hard, and [npc.she] lets out [npc.a_moan+] as a slick stream of precum oozes out of the tip as its production [style.boldGrow(increases)].</br>"
							+ "[npc.She] now has [style.boldSex(" + UtilText.generateSingluarDeterminer(wetnessDescriptor) + " " + wetnessDescriptor + " urethra)]!"
						+ "</p>");
			}
			
		} else {
			if (owner.isPlayer()) {
				return "<p>"
							+ "You shift about uncomfortably and let out a frustrated groan as you feel your precum production [style.boldShrink(decrease)].</br>"
							+ "You now have [style.boldSex(" + UtilText.generateSingluarDeterminer(wetnessDescriptor) + " " + wetnessDescriptor + " urethra)]!"
						+ "</p>";
			} else {
				return UtilText.parse(owner, 
						"<p>"
							+ "[npc.Name] shifts about uncomfortably and lets out a frustrated groan as [npc.she] feels [npc.her] precum production [style.boldShrink(decrease)].</br>"
							+ "[npc.She] now has [style.boldSex(" + UtilText.generateSingluarDeterminer(wetnessDescriptor) + " " + wetnessDescriptor + " urethra)]!"
						+ "</p>");
			}
		}
	}

	@Override
	public Capacity getCapacity() {
		return Capacity.getCapacityFromValue((int)capacity);
	}

	@Override
	public float getRawCapacityValue() {
		return capacity;
	}

	@Override
	public String setCapacity(GameCharacter owner, float capacity) {
		float capacityChange = 0;
		
		if (capacity <= 0) {
			if (this.capacity != 0) {
				capacityChange = 0 - this.capacity;
				this.capacity = 0;
			}
		} else if (capacity >= Capacity.SEVEN_GAPING.getMaximumValue()) {
			if (this.capacity != Capacity.SEVEN_GAPING.getMaximumValue()) {
				capacityChange = Capacity.SEVEN_GAPING.getMaximumValue() - this.capacity;
				this.capacity = Capacity.SEVEN_GAPING.getMaximumValue();
			}
		} else {
			if (this.capacity != capacity) {
				capacityChange = capacity - this.capacity;
				this.capacity = capacity;
			}
		}
		
		if(capacityChange == 0) {
			if(owner.isPlayer()) {
				return "<p style='text-align:center;'>[style.colourDisabled(Your urethra's capacity doesn't change...)]</p>";
			} else {
				return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(The capacity of [npc.name]'s urethra doesn't change...)]</p>");
			}
		}
		
		String capacityDescriptor = getCapacity().getDescriptor();
		if (capacityChange > 0) {
			if (owner.isPlayer()) {
				return "<p>"
							+ "You let out a shocked gasp as you feel your urethra dilate and stretch out as its internal [style.boldGrow(capacity increases)].</br>"
							+ "The transformation quickly pcockes, leaving you with [style.boldSex(" + UtilText.generateSingluarDeterminer(capacityDescriptor) + " " + capacityDescriptor + " urethra)]!"
						+ "</p>";
			} else {
				return UtilText.parse(owner, 
						"<p>"
							+ "[npc.Name] lets out a shocked gasp as [npc.she] feels [npc.her] urethra dilate and stretch out as its internal [style.boldGrow(capacity increases)].</br>"
							+ "The transformation quickly pcockes, leaving [npc.herHim] with [style.boldSex(" + UtilText.generateSingluarDeterminer(capacityDescriptor) + " " + capacityDescriptor + " urethra)]!"
						+ "</p>");
			}
			
		} else {
			if (owner.isPlayer()) {
				return "<p>"
							+ "You let out a cry as you feel your urethra uncontrollably tighten and clench as its internal [style.boldShrink(capacity decreases)].</br>"
							+ "The transformation quickly pcockes, leaving you with [style.boldSex(" + UtilText.generateSingluarDeterminer(capacityDescriptor) + " " + capacityDescriptor + " urethra)]!"
						+ "</p>";
			} else {
				return UtilText.parse(owner, 
						"<p>"
							+ "[npc.Name] lets out a cry as [npc.she] feels [npc.her] urethra uncontrollably tighten and clench as its internal [style.boldShrink(capacity decreases)].</br>"
							+ "The transformation quickly pcockes, leaving [npc.herHim] with [style.boldSex(" + UtilText.generateSingluarDeterminer(capacityDescriptor) + " " + capacityDescriptor + " urethra)]!"
						+ "</p>");
			}
		}
	}
	
	@Override
	public float getStretchedCapacity() {
		return stretchedCapacity;
	}

	@Override
	public boolean setStretchedCapacity(float stretchedCapacity) {
		if (stretchedCapacity <= 0) {
			if (this.stretchedCapacity != 0) {
				this.stretchedCapacity = 0;
				return true;
			}
		} else if (stretchedCapacity >= Capacity.SEVEN_GAPING.getMaximumValue()) {
			if (this.stretchedCapacity != Capacity.SEVEN_GAPING.getMaximumValue()) {
				this.stretchedCapacity = Capacity.SEVEN_GAPING.getMaximumValue();
				return true;
			}
		} else {
			if (this.stretchedCapacity != stretchedCapacity) {
				this.stretchedCapacity = stretchedCapacity;
				return true;
			}
		}
		return false;
	}

	@Override
	public OrificeElasticity getElasticity() {
		return OrificeElasticity.getElasticityFromInt(elasticity);
	}

	@Override
	public String setElasticity(GameCharacter owner, int elasticity) {
		float elasticityChange = 0;
		
		if (elasticity <= 0) {
			if (this.elasticity != 0) {
				elasticityChange = 0 - this.elasticity;
				this.elasticity = 0;
			}
		} else if (elasticity >= OrificeElasticity.SEVEN_ELASTIC.getValue()) {
			if (this.elasticity != OrificeElasticity.SEVEN_ELASTIC.getValue()) {
				elasticityChange = OrificeElasticity.SEVEN_ELASTIC.getValue() - this.elasticity;
				this.elasticity = OrificeElasticity.SEVEN_ELASTIC.getValue();
			}
		} else {
			if (this.elasticity != elasticity) {
				elasticityChange = elasticity - this.elasticity;
				this.elasticity = elasticity;
			}
		}
		
		if(elasticityChange == 0) {
			if(owner.isPlayer()) {
				return "<p style='text-align:center;'>[style.colourDisabled(Your urethra's elasticity doesn't change...)]</p>";
			} else {
				return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(The elasticity of [npc.name]'s urethra doesn't change...)]</p>");
			}
		}
		
		String elasticityDescriptor = getElasticity().getDescriptor();
		if (elasticityChange > 0) {
			if (owner.isPlayer()) {
				return "<p>"
							+ "You let out a little gasp as you feel a strange slackening sensation pulsating deep within your [pc.cock] as your urethra's [style.boldGrow(elasticity increases)].</br>"
							+ "The transformation quickly pcockes, leaving you with [style.boldSex(" + UtilText.generateSingluarDeterminer(elasticityDescriptor) + " " + elasticityDescriptor + " urethra)]!"
						+ "</p>";
			} else {
				return UtilText.parse(owner, 
						"<p>"
							+ "[npc.Name] lets out a little gasp as [npc.she] feels a strange slackening sensation pulsating deep within [npc.her] [npc.cock] as [npc.her] urethra's [style.boldGrow(elasticity increases)].</br>"
							+ "The transformation quickly pcockes, leaving [npc.herHim] with [style.boldSex(" + UtilText.generateSingluarDeterminer(elasticityDescriptor) + " " + elasticityDescriptor + " urethra)]!"
						+ "</p>");
			}
			
		} else {
			if (owner.isPlayer()) {
				return "<p>"
							+ "You let out a little gasp as you feel a strange clenching sensation pulsating deep within your [pc.cock] as your urethra's [style.boldShrink(elasticity decreases)].</br>"
							+ "The transformation quickly pcockes, leaving you with [style.boldSex(" + UtilText.generateSingluarDeterminer(elasticityDescriptor) + " " + elasticityDescriptor + " urethra)]!"
						+ "</p>";
			} else {
				return UtilText.parse(owner, 
						"<p>"
							+ "[npc.Name] lets out a little gasp as [npc.she] feels a strange clenching sensation pulsating deep within [npc.her] [npc.cock] as [npc.her] urethra's [style.boldShrink(elasticity decreases)].</br>"
							+ "The transformation quickly pcockes, leaving [npc.herHim] with [style.boldSex(" + UtilText.generateSingluarDeterminer(elasticityDescriptor) + " " + elasticityDescriptor + " urethra)]!"
						+ "</p>");
			}
		}
	}
	
	@Override
	public OrificePlasticity getPlasticity() {
		return OrificePlasticity.getElasticityFromInt(plasticity);
	}

	@Override
	public String setPlasticity(GameCharacter owner, int plasticity) {
		float plasticityChange = 0;
		
		if (plasticity <= 0) {
			if (this.plasticity != 0) {
				plasticityChange = 0 - this.plasticity;
				this.plasticity = 0;
			}
		} else if (plasticity >= OrificePlasticity.SEVEN_MOULDABLE.getValue()) {
			if (this.plasticity != OrificePlasticity.SEVEN_MOULDABLE.getValue()) {
				plasticityChange = OrificePlasticity.SEVEN_MOULDABLE.getValue() - this.plasticity;
				this.plasticity = OrificePlasticity.SEVEN_MOULDABLE.getValue();
			}
		} else {
			if (this.plasticity != plasticity) {
				plasticityChange = plasticity - this.plasticity;
				this.plasticity = plasticity;
			}
		}
		
		if(plasticityChange == 0) {
			if(owner.isPlayer()) {
				return "<p style='text-align:center;'>[style.colourDisabled(Your urethra's plasticity doesn't change...)]</p>";
			} else {
				return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(The plasticity of [npc.name]'s urethra doesn't change...)]</p>");
			}
		}
		
		String plasticityDescriptor = getPlasticity().getDescriptor();
		if (plasticityChange > 0) {
			if (owner.isPlayer()) {
				return "<p>"
							+ "You let out a little gasp as you feel a strange moulding sensation pulsating deep within your [pc.cock] as your urethra's [style.boldGrow(plasticity increases)].</br>"
							+ "The transformation quickly pcockes, leaving you with [style.boldSex(" + UtilText.generateSingluarDeterminer(plasticityDescriptor) + " " + plasticityDescriptor + " urethra)]!"
						+ "</p>";
			} else {
				return UtilText.parse(owner, 
						"<p>"
							+ "[npc.Name] lets out a little gasp as [npc.she] feels a strange moulding sensation pulsating deep within [npc.her] [npc.cock] as [npc.her] urethra's [style.boldGrow(plasticity increases)].</br>"
							+ "The transformation quickly pcockes, leaving [npc.herHim] with [style.boldSex(" + UtilText.generateSingluarDeterminer(plasticityDescriptor) + " " + plasticityDescriptor + " urethra)]!"
						+ "</p>");
			}
			
		} else {
			if (owner.isPlayer()) {
				return "<p>"
							+ "You let out a little gasp as you feel a strange softening sensation pulsating deep within your [pc.cock] as your urethra's [style.boldShrink(plasticity decreases)].</br>"
							+ "The transformation quickly pcockes, leaving you with [style.boldSex(" + UtilText.generateSingluarDeterminer(plasticityDescriptor) + " " + plasticityDescriptor + " urethra)]!"
						+ "</p>";
			} else {
				return UtilText.parse(owner, 
						"<p>"
							+ "[npc.Name] lets out a little gasp as [npc.she] feels a strange softening sensation pulsating deep within [npc.her] [npc.cock] as [npc.her] urethra's [style.boldShrink(plasticity decreases)].</br>"
							+ "The transformation quickly pcockes, leaving [npc.herHim] with [style.boldSex(" + UtilText.generateSingluarDeterminer(plasticityDescriptor) + " " + plasticityDescriptor + " urethra)]!"
						+ "</p>");
			}
		}
	}

	@Override
	public boolean isVirgin() {
		return virgin;
	}

	@Override
	public void setVirgin(boolean virgin) {
		this.virgin = virgin;
	}

	@Override
	public boolean hasOrificeModifier(OrificeModifier modifier) {
		return orificeModifiers.contains(modifier);
	}

	@Override
	public String addOrificeModifier(GameCharacter owner, OrificeModifier modifier) {
		if(hasOrificeModifier(modifier)) {
			return "<p style='text-align:center;'>[style.colourDisabled(Nothing happens...)]</p>";
		}
		
		orificeModifiers.add(modifier);
		
		switch(modifier) {
			case MUSCLE_CONTROL:
				if(owner.isPlayer()) {
					return "<p>"
								+ "You feel an intense pressure swelling up deep within your [pc.cock], but before you have any chance to react, the feeling suddenly fades away."
								+ " With an experimental clench, you discover that the interior of your urethra is now lined with [style.boldGrow(muscles)], which you can use to expertly grip and squeeze down on any penetrating object.</br>"
								+ "[style.boldSex(Your urethra is now lined with an intricate series of muscles!)]"
							+ "</p>";
				} else {
					return "<p>"
								+ "[npc.Name] lets out a little cry as an intense pressure swells up deep within [npc.her] [npc.cock], but before [npc.she] has any chance to react, the feeling quickly dissipates."
								+ " With an experimental clench, [npc.she] discovers that the interior of [npc.her] urethra is now lined with [style.boldGrow(muscles)], which [npc.she] can use to expertly grip and squeeze down on any penetrating object.</br>"
								+ "[style.boldSex([npc.Name]'s urethra is now lined with an intricate series of muscles!)]"
							+ "</p>";
				}
			case RIBBED:
				if(owner.isPlayer()) {
					return "<p>"
								+ "You feel an intense pressure swelling up deep within your [pc.cock], but before you have any chance to react, the feeling suddenly fades away."
								+ " Shifting your [pc.cock] around a little, you feel that the inside of your urethra is now lined with [style.boldGrow(fleshy, highly-sensitive ribs)], which provide extreme pleasure when stimulated.</br>"
								+ "[style.boldSex(Your urethra is now lined with fleshy, pleasure-inducing ribs!)]"
							+ "</p>";
				} else {
					return "<p>"
							+ "[npc.Name] lets out a little cry as an intense pressure swells up deep within [npc.her] [npc.cock], but before [npc.she] has any chance to react, the feeling quickly dissipates."
							+ " Shifting [npc.her] [npc.cock] around a little, [npc.she] discovers that the inside of [npc.her] urethra is now lined with [style.boldGrow(fleshy, highly-sensitive ribs)],"
								+ " which provide extreme pleasure when stimulated.</br>"
							+ "[style.boldSex([npc.Name]'s urethra is now lined with fleshy, pleasure-inducing ribs!)]"
						+ "</p>";
				}
			case TENTACLED:
				if(owner.isPlayer()) {
					return "<p>"
								+ "You feel an intense pressure swelling up deep within your [pc.cock], but before you have any chance to react, the feeling suddenly fades away."
								+ " A surprised cry bursts out from your mouth as you feel that the inside of your urethra is now filled with [style.boldGrow(a series of little wriggling tentacles)], over which you have limited control.</br>"
								+ "[style.boldSex(The inside of your urethra is now filled with little tentacles, which wriggle with a mind of their own!)]"
							+ "</p>";
				} else {
					return "<p>"
								+ "[npc.Name] lets out a little cry as an intense pressure swells up deep within [npc.her] [npc.cock], but before [npc.she] has any chance to react, the feeling quickly dissipates."
								+ " With an experimental clench, [npc.she] discovers that the inside of [npc.her] urethra is now filled with [style.boldGrow(a series of little wriggling tentacles)], over which [npc.she] has limited control.</br>"
								+ "[style.boldSex(The inside of [npc.name]'s urethra is now filled with little tentacles, which wriggle with a mind of their own!)]"
							+ "</p>";
				}
			case PUFFY:
				if(owner.isPlayer()) {
					return "<p>"
								+ "You feel a tingling sensation running over your [pc.cock], and you let out a little cry as you feel the rim of your urethra [style.boldGrow(puff up)] into a doughnut-like ring.</br>"
								+ "[style.boldSex(The rim of your urethra is now swollen and puffy!)]"
							+ "</p>";
				} else {
					return "<p>"
								+ "[npc.Name] lets out a little cry as [npc.she] feels a tingling sensation running over [npc.her] [npc.cock], before the rim of [npc.her] urethra [style.boldGrow(puffs up)] into a doughnut-like ring.</br>"
								+ "[style.boldSex(The rim of [npc.name]'s urethra is now swollen and puffy!)]"
							+ "</p>";
				}
		}
		
		// Catch:
		return "<p style='text-align:center;'>[style.colourDisabled(Nothing happens...)]</p>";
	}

	@Override
	public String removeOrificeModifier(GameCharacter owner, OrificeModifier modifier) {
		if(!hasOrificeModifier(modifier)) {
			return "<p style='text-align:center;'>[style.colourDisabled(Nothing happens...)]</p>";
		}
		
		orificeModifiers.remove(modifier);
		
		switch(modifier) {
			case MUSCLE_CONTROL:
				if(owner.isPlayer()) {
					return "<p>"
								+ "You feel an intense pressure swelling up deep within your [pc.cock], but before you have any chance to react, the feeling suddenly fades away."
								+ " With an experimental clench, you discover that the interior of your urethra has lost its [style.boldShrink(extra muscles)].</br>"
								+ "[style.boldSex(Your urethra is no longer lined with an intricate series of muscles!)]"
							+ "</p>";
				} else {
					return "<p>"
								+ "[npc.Name] lets out a little cry as an intense pressure swells up deep within [npc.her] [npc.cock], but before [npc.she] has any chance to react, the feeling quickly dissipates."
								+ " With an experimental clench, [npc.she] discovers that the interior of [npc.her] urethra has lost its [style.boldShrink(extra muscles)].</br>"
								+ "[style.boldSex([npc.Name]'s urethra is no longer lined with an intricate series of muscles!)]"
							+ "</p>";
				}
			case RIBBED:
				if(owner.isPlayer()) {
					return "<p>"
								+ "You feel an intense pressure swelling up deep within your [pc.cock], but before you have any chance to react, the feeling suddenly fades away."
								+ " Shifting your [pc.cock] around a little, you feel that the [style.boldShrink(fleshy, highly-sensitive ribs)] that once lined your urethra have vanished.</br>"
								+ "[style.boldSex(Your urethra is no longer lined with fleshy, pleasure-inducing ribs!)]"
							+ "</p>";
				} else {
					return "<p>"
							+ "[npc.Name] lets out a little cry as an intense pressure swells up deep within [npc.her] [npc.cock], but before [npc.she] has any chance to react, the feeling quickly dissipates."
							+ " Shifting [npc.her] [npc.cock] around a little, [npc.she] discovers that the [style.boldShrink(fleshy, highly-sensitive ribs)] that once lined [npc.her] urethra have vanished.</br>"
							+ "[style.boldSex([npc.Name]'s urethra is no longer lined with fleshy, pleasure-inducing ribs!)]"
						+ "</p>";
				}
			case TENTACLED:
				if(owner.isPlayer()) {
					return "<p>"
								+ "You feel an intense pressure swelling up deep within your [pc.cock], but before you have any chance to react, the feeling suddenly fades away."
								+ " A surprised cry bursts out from your mouth as you feel that the [style.boldShrink(series of little wriggling tentacles)] within your urethra have all disappeared.</br>"
								+ "[style.boldSex(The inside of your urethra is no longer filled with little tentacles!)]"
							+ "</p>";
				} else {
					return "<p>"
								+ "[npc.Name] lets out a little cry as an intense pressure swells up deep within [npc.her] [npc.cock], but before [npc.she] has any chance to react, the feeling quickly dissipates."
								+ " With an experimental clench, [npc.she] discovers that the [style.boldShrink(series of little wriggling tentacles)] within [npc.her] urethra have all disappeared.</br>"
								+ "[style.boldSex(The inside of [npc.name]'s urethra is no longer filled with little tentacles!)]"
							+ "</p>";
				}
			case PUFFY:
				if(owner.isPlayer()) {
					return "<p>"
								+ "You feel a tingling sensation running over your [pc.cock], and you let out a little cry as you feel the puffy rim of your urethra [style.boldGrow(deflate)] into a more normal-looking shape.</br>"
								+ "[style.boldSex(The rim of your urethra is no longer swollen and puffy!)]"
							+ "</p>";
				} else {
					return "<p>"
								+ "[npc.Name] lets out a little cry as [npc.she] feels a tingling sensation running over [npc.her] [npc.cock],"
									+ " before the puffy rim of [npc.her] urethra [style.boldShrink(deflates)] into a more normal-looking shape.</br>"
								+ "[style.boldSex(The rim of [npc.name]'s urethra is no longer swollen and puffy!)]"
							+ "</p>";
				}
		}
		
		// Catch:
		return "<p style='text-align:center;'>[style.colourDisabled(Nothing happens...)]</p>";
	}

}
