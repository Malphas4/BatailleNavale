package M1.reseau.model.player.visitor;

import M1.reseau.model.exception.IJoueurException;
import M1.reseau.model.player.bot.EasyBot;
import M1.reseau.model.player.bot.HardBot;
import M1.reseau.model.player.bot.MediumBot;
import M1.reseau.model.player.classic.JoueurNormal;

public interface IVisitorJoueur {

    void visite(JoueurNormal _joueur) throws IJoueurException;
    void visite(EasyBot _bot) throws IJoueurException;
    void visite(MediumBot _bot) throws IJoueurException;
    void visite(HardBot _bot) throws IJoueurException;
}
