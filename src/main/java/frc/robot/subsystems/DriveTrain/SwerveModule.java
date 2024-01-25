package frc.robot.subsystems.DriveTrain;

import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.math.kinematics.SwerveModuleState;

public interface SwerveModule {

    SwerveModulePosition getPosition();
    void setDesiredState(SwerveModuleState desiredState);

    SwerveModuleState getState();

    SwerveModuleState getSetState();

    double getSwerveEncoderPosition();

    void resetEncoders();

    void update();
}