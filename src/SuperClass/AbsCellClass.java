package SuperClass;

import Interfaces.ICell;
import Interfaces.IPosition;
import Interfaces.IWorld;
import ToolClass.Position2D;

public class AbsCellClass implements ICell {
    protected Position2D pos;
    protected ICell.State currentState;
    protected ICell.State futureState;
    protected IWorld world;

    @Override
    public IPosition getPosition() {
        return this.pos;
    }

    @Override
    public State getState() {
        return currentState;
    }

    @Override
    public void setState(State s) {
        this.currentState = s;
    }

    ////
    @Override
    public void beforeEvolve() {
        System.out.println("before Evolve is not implemented");
    }

    @Override
    public void evolve() {
        setState(this.futureState);
    }

    @Override
    public void postEvolve() {

    }
}
