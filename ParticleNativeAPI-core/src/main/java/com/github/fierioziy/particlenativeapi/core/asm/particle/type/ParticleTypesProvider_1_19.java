package com.github.fierioziy.particlenativeapi.core.asm.particle.type;

import com.github.fierioziy.particlenativeapi.core.asm.packet.ParticlePacketProvider;
import com.github.fierioziy.particlenativeapi.core.asm.particle.type.v1_17.*;
import com.github.fierioziy.particlenativeapi.core.asm.particle.type.v1_19.ParticleTypeShriekASM_1_19;
import com.github.fierioziy.particlenativeapi.core.asm.particle.type.v1_19.ParticleTypeVibrationASM_1_19;
import com.github.fierioziy.particlenativeapi.core.asm.particle.type.v1_19.ParticleTypeSculkChargeASM_1_19;
import com.github.fierioziy.particlenativeapi.core.asm.skeleton.ClassSkeleton;
import com.github.fierioziy.particlenativeapi.core.asm.utils.InternalResolver;

/**
 * <p>Class responsible for providing version-dependent code of
 * particle types in MC 1.19.</p>
 */
public class ParticleTypesProvider_1_19 extends ParticleTypesProvider_1_18 {

    public ParticleTypesProvider_1_19(InternalResolver resolver,
                                      ParticlePacketProvider particlePacketProvider) {
        super(resolver, "_1_19", particlePacketProvider);
    }

    @Override
    public void registerClasses() {
        new ParticleTypeASM_1_17(
                internal, suffix,
                ClassSkeleton.PARTICLE_TYPE,
                particlePacketImpl_X)
                .registerClass();
        new ParticleTypeASM_1_17(
                internal, suffix,
                ClassSkeleton.PARTICLE_TYPE_MOTION,
                particlePacketImpl_X)
                .registerClass();
        new ParticleTypeASM_1_17(
                internal, suffix,
                ClassSkeleton.PARTICLE_TYPE_COLORABLE,
                particlePacketImpl_X)
                .registerClass();
        new ParticleTypeASM_1_17(
                internal, suffix,
                ClassSkeleton.PARTICLE_TYPE_NOTE,
                particlePacketImpl_X)
                .registerClass();

        new ParticleTypeBlockASM_1_17(
                internal, suffix,
                ClassSkeleton.PARTICLE_TYPE_BLOCK,
                ClassSkeleton.PARTICLE_TYPE)
                .registerClass();
        new ParticleTypeBlockASM_1_17(
                internal, suffix,
                ClassSkeleton.PARTICLE_TYPE_BLOCK_MOTION,
                ClassSkeleton.PARTICLE_TYPE_MOTION)
                .registerClass();

        new ParticleTypeDustASM_1_17(
                internal, suffix,
                ClassSkeleton.PARTICLE_TYPE_DUST,
                ClassSkeleton.PARTICLE_TYPE)
                .registerClass();
        new ParticleTypeDustTransitionASM_1_17(
                internal, suffix,
                ClassSkeleton.PARTICLE_TYPE_DUST_COLOR_TRANSITION,
                ClassSkeleton.PARTICLE_TYPE)
                .registerClass();

        new ParticleTypeItemASM_1_17(
                internal, suffix,
                ClassSkeleton.PARTICLE_TYPE_ITEM_MOTION,
                ClassSkeleton.PARTICLE_TYPE_MOTION)
                .registerClass();

        new ParticleTypeVibrationASM_1_19(
                internal, suffix,
                ClassSkeleton.PARTICLE_TYPE_VIBRATION,
                particlePacketImpl_X)
                .registerClass();

        new ParticleTypeRedstoneASM_1_17(
                internal, suffix,
                particlePacketImpl_X)
                .registerClass();

        new ParticleTypeSculkChargeASM_1_19(
                internal, suffix,
                ClassSkeleton.PARTICLE_TYPE_SCULK_CHARGE_MOTION,
                ClassSkeleton.PARTICLE_TYPE_MOTION)
                .registerClass();

        new ParticleTypeShriekASM_1_19(
                internal, suffix,
                ClassSkeleton.PARTICLE_TYPE_SHRIEK,
                ClassSkeleton.PARTICLE_TYPE)
                .registerClass();
    }

}