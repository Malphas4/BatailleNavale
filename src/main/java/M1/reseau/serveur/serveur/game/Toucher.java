package M1.reseau.serveur.serveur.game;

import M1.reseau.model.world.element.ICase;
import M1.reseau.model.world.element.classic.CaseNormal;

public class Toucher {
    private ICase _case1;
    private ICase _case2;

    public Toucher() {
        _case1 = new CaseNormal(-1, -1);
        _case2 = new CaseNormal(-1, -1);
    }

    public ICase get_case1() {
        return _case1;
    }

    public void set_case1(ICase _case1) {
        this._case1 = _case1;
    }

    public ICase get_case2() {
        return _case2;
    }

    public void set_case2(ICase _case2) {
        this._case2 = _case2;
    }
}
