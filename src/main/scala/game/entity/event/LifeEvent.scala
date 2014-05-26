package game.entity.event

import org.joda.time.DateTime




trait LifeEvent {
	def eventType:LifeEventType;
    def date:DateTime;
    def character:Character;
    def description:String;
}
