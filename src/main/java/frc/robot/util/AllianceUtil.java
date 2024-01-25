package frc.robot.util;


import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import frc.robot.Constants.FieldConstants;

public class AllianceUtil {
    public static Pose2d allianceToField(Pose2d alliancePose) {
        return DriverStation.getAlliance()
                .map(alliance -> {
                    switch (alliance) {
                        case Blue:
                            return alliancePose;
                        case Red:
                            return new Pose2d(
                                    new Translation2d(
                                            FieldConstants.fieldLength - alliancePose.getX(),
                                            alliancePose.getY()
                                    ),
                                    alliancePose.getRotation().minus(Rotation2d.fromRadians(Math.PI))
                            );
                    }
                    return null; // or throw an exception for unsupported alliance
                })
                .orElse(null);
    }

    public static Pose2d fieldToAlliance(Pose2d fieldPose) {
        return DriverStation.getAlliance()
                .map(alliance -> {
                    switch (alliance) {
                        case Blue:
                            return fieldPose;
                        case Red:
                            return new Pose2d(
                                    new Translation2d(
                                            FieldConstants.fieldLength - fieldPose.getX(),
                                            fieldPose.getY()
                                    ),
                                    fieldPose.getRotation().minus(Rotation2d.fromRadians(Math.PI))
                            );
                    }
                    return null; // or throw an exception for unsupported alliance
                })
                .orElse(null);
    }

    public static boolean isBlue() {
        return DriverStation.getAlliance().map(Alliance.Blue::equals).orElse(false);
    }

    public static double getDistanceFromAlliance(Pose2d pose) {
        return isBlue() ? pose.getX() : FieldConstants.fieldLength - pose.getX();
    }

    public static Rotation2d getFieldOrientationZero() {
        return Rotation2d.fromRadians(isBlue() ? 0 : Math.PI);
    }
}
