package frc.robot.commands;

import java.util.Set;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.Constants.DriveConstants;
import frc.robot.subsystems.DriveTrain.Drivetrain;


public class DriveWithJoysticks extends Command {
    private final Drivetrain drivetrain;


    private final Joystick translation;

    public DriveWithJoysticks(Drivetrain drivetrain,Joystick translation) {
        this.drivetrain = drivetrain;


        this.translation = translation;
        drivetrain.resetEncoders();
    }

    @Override
    public void execute() {
        //double sensitivity = RobotContainer.joystickLeft.getRawButtonPressed(5)? 0.25: 1;

        // Negative because joysticks are inverted
        double ty = MathUtil.applyDeadband(-translation.getRawAxis(0), 0.1);
        double tx = MathUtil.applyDeadband(-translation.getRawAxis(1), 0.1);
        double r = MathUtil.applyDeadband(-translation.getRawAxis(2), 0.1);

        double vx = tx * DriveConstants.MAX_SPEED_METERS_PER_SECOND;
        double vy = ty * DriveConstants.MAX_SPEED_METERS_PER_SECOND;
        double omega = r * DriveConstants.MAX_ANGULAR_SPEED;

        ChassisSpeeds fieldRelSpeeds = new ChassisSpeeds(vx, vy, omega);

        drivetrain.drive(fieldRelSpeeds);
    }

    @Override
    public Set<Subsystem> getRequirements() {
        return Set.of(drivetrain);
    }

    public void resetFieldOrientation() {}
}