package tech.tresearchgroup.babygalago.model;

import io.activej.serializer.annotations.Deserialize;
import io.activej.serializer.annotations.Serialize;
import io.activej.serializer.annotations.SerializeNullable;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import tech.tresearchgroup.palila.model.BasicUserObjectInterface;
import tech.tresearchgroup.palila.model.LockType;
import tech.tresearchgroup.palila.model.enums.PermissionGroupEnum;
import tech.tresearchgroup.schemas.galago.entities.UserSettingsEntity;

import java.util.Date;

@Setter
@ToString
@NoArgsConstructor
public class ExtendedUserEntity implements BasicUserObjectInterface {
    private transient Date created;

    private transient Date updated;

    private Long id;

    private LockType lockType;

    private PermissionGroupEnum permissionGroup;

    private String username;

    private String email;

    private String password;

    private String apiKey;
    private UserSettingsEntity userSettings;

    public ExtendedUserEntity(@Deserialize("created") Date created,
                              @Deserialize("updated") Date updated,
                              @Deserialize("id") Long id,
                              @Deserialize("lockType") LockType lockType,
                              @Deserialize("permissionGroup") PermissionGroupEnum permissionGroup,
                              @Deserialize("username") String username,
                              @Deserialize("email") String email,
                              @Deserialize("password") String password,
                              @Deserialize("apiKey") String apiKey,
                              @Deserialize("userSettings") UserSettingsEntity userSettings) {
        this.setCreated(created);
        this.setUpdated(updated);
        this.setId(id);
        this.setLockType(lockType);
        this.setPermissionGroup(permissionGroup);
        this.setUsername(username);
        this.setEmail(email);
        this.setPassword(password);
        this.setApiKey(apiKey);
        this.userSettings = userSettings;
    }

    @Override
    @Serialize(order = 0)
    @SerializeNullable
    public Date getCreated() {
        return created;
    }

    @Override
    @Serialize(order = 1)
    @SerializeNullable
    public Date getUpdated() {
        return updated;
    }

    @Override
    @Serialize(order = 2)
    @SerializeNullable
    public Long getId() {
        return id;
    }

    @Override
    @Serialize(order = 3)
    @SerializeNullable
    public LockType getLockType() {
        return lockType;
    }

    @Override
    @Serialize(order = 4)
    @SerializeNullable
    public PermissionGroupEnum getPermissionGroup() {
        return permissionGroup;
    }

    @Override
    @Serialize(order = 5)
    @SerializeNullable
    public String getUsername() {
        return username;
    }

    @Override
    @Serialize(order = 6)
    @SerializeNullable
    public String getEmail() {
        return email;
    }

    @Override
    @Serialize(order = 7)
    @SerializeNullable
    public String getPassword() {
        return password;
    }

    @Override
    @Serialize(order = 8)
    @SerializeNullable
    public String getApiKey() {
        return apiKey;
    }

    @Serialize(order = 9)
    @SerializeNullable
    public UserSettingsEntity getUserSettings() {
        return userSettings;
    }
}
