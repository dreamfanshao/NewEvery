/**
 * Copyright (c) 2023, CCSSOFT All Rights Reserved.
 */
package com.atguigu.ssyx.acl.utils;

import com.atguigu.ssyx.model.acl.Permission;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * queryPermissionHelp
 * </p>
 *
 * @author wangfeifan
 * @Version: 1.0
 * @since 7月 06, 2023
 */
public class queryPermissionHelp {

    public static List<Permission> buildPermission(List<Permission> allPermissionList) {
        List<Permission> tree = new ArrayList<>();
        for (Permission permission:allPermissionList) {
            if(permission.getPid()==0){
                permission.setLevel(1);
                tree.add(findChildPermission(permission,allPermissionList));
            }
        }
        return tree;
    }

    private static Permission findChildPermission(Permission permission, List<Permission> list) {
        permission.setChildren(new ArrayList<Permission>());
        for (Permission it:list) {
            if(permission.getId().longValue()==it.getPid().longValue()){
                int level = permission.getLevel()+1;
                it.setLevel(level);
                if (permission.getChildren()==null){
                    permission.setChildren(new ArrayList<>());
                }
                permission.getChildren().add(findChildPermission(it,list));
            }


        }
        return permission;
    }
}
