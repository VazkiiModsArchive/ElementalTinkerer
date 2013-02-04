/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 26 Jan 2013
package vazkii.tinkerer.helper;

/**
 * ObjectPair
 *
 * A Generic Pair of Objects.
 *
 * @author Vazkii
 */
public class ObjectPair<T1, T2> {

	public T1 object1;
	public T2 object2;
	
	public ObjectPair(T1 t1, T2 t2) {
		object1 = t1;
		object2 = t2;
	}
	
}
