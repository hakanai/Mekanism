/*package mekanism.common.integration.energy.fluxnetworks;

import mekanism.api.Action;
import mekanism.api.energy.IStrictEnergyHandler;
import mekanism.api.math.FloatingLong;
import mekanism.common.integration.energy.EnergyCompatUtils.EnergyType;
import sonar.fluxnetworks.api.energy.IFNEnergyStorage;

public class FNIntegration implements IFNEnergyStorage {

    private final IStrictEnergyHandler handler;

    public FNIntegration(IStrictEnergyHandler handler) {
        this.handler = handler;
    }

    @Override
    public long receiveEnergyL(long maxReceive, boolean simulate) {
        if (maxReceive <= 0) {
            return 0;
        }
        FloatingLong toInsert = EnergyType.FORGE.convertFrom(maxReceive);
        return EnergyType.FORGE.convertToAsLong(toInsert.subtract(handler.insertEnergy(toInsert, Action.get(!simulate))));
    }

    @Override
    public long extractEnergyL(long maxExtract, boolean simulate) {
        return maxExtract <= 0 ? 0 : EnergyType.FORGE.convertToAsLong(handler.extractEnergy(EnergyType.FORGE.convertFrom(maxExtract), Action.get(!simulate)));
    }

    @Override
    public long getEnergyStoredL() {
        int containers = handler.getEnergyContainerCount();
        if (containers > 0) {
            long energy = 0;
            for (int container = 0; container < containers; container++) {
                long total = EnergyType.FORGE.convertToAsLong(handler.getEnergy(container));
                if (total > Long.MAX_VALUE - energy) {
                    //Ensure we don't overflow
                    energy = Long.MAX_VALUE;
                    break;
                } else {
                    energy += total;
                }
            }
            return energy;
        }
        return 0;
    }

    @Override
    public long getMaxEnergyStoredL() {
        int containers = handler.getEnergyContainerCount();
        if (containers > 0) {
            long maxEnergy = 0;
            for (int container = 0; container < containers; container++) {
                long max = EnergyType.FORGE.convertToAsLong(handler.getMaxEnergy(container));
                if (max > Long.MAX_VALUE - maxEnergy) {
                    //Ensure we don't overflow
                    maxEnergy = Long.MAX_VALUE;
                    break;
                } else {
                    maxEnergy += max;
                }
            }
            return maxEnergy;
        }
        return 0;
    }

    @Override
    public boolean canExtractL() {
        //Mark that we can receive energy if we can insert energy
        if (!handler.extractEnergy(FloatingLong.ONE, Action.SIMULATE).isZero()) {
            return true;
        }
        //Or all our containers are empty. This isn't fully accurate but will give the best
        // accuracy to other mods of if we may be able to extract given we are predicate based
        // instead of having strict can receive checks
        for (int container = 0; container < handler.getEnergyContainerCount(); container++) {
            if (!handler.getEnergy(container).isZero()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean canReceiveL() {
        //Mark that we can receive energy if we can insert energy
        if (handler.insertEnergy(FloatingLong.ONE, Action.SIMULATE).smallerThan(FloatingLong.ONE)) {
            return true;
        }
        //Or all our containers are full. This isn't fully accurate but will give the best
        // accuracy to other mods of if we may be able to receive given we are predicate based
        // instead of having strict can receive checks
        for (int container = 0; container < handler.getEnergyContainerCount(); container++) {
            if (!handler.getNeededEnergy(container).isZero()) {
                return false;
            }
        }
        return true;
    }
}*/