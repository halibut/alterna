package game.entity
import java.util.Date

trait LifeEvent {

    def date:Date;
    def character:Character;
    def description:String;
}
