package net.lanternmc.r1_8.PlayerManager;

import java.lang.reflect.InvocationTargetException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

import org.bukkit.entity.Player;

public class Identifier
{

	public static String findMacAddress(Player p) throws SocketException, UnknownHostException
	{
		InetAddress add = p.getAddress().getAddress();
		String macAddress = "";

		        NetworkInterface ni = NetworkInterface.getByInetAddress(add);
		        if (ni != null)
		        {
		            byte[] mac = ni.getHardwareAddress();
		            if (mac != null)
		            {

		                for (int k = 0; k < mac.length; k++)
		                {
		                	macAddress = String.format("%02X%s", mac[k], (k < mac.length - 1) ? "-" : "");
		                }
		                return macAddress;
		            }
		            else
		            {
		                return null;
		            }
		        }
		        else
		        {
		            return null;
		        }
		}

}